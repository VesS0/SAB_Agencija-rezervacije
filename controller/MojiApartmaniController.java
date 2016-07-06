/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Apartman;
import entity.Korisnik;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MojiApartmaniModel;
import session.MySession;
import view.IzmenaApartmanaView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class MojiApartmaniController implements Initializable {

    @FXML
    private TableView MojiApartmaniTable;

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
        MojiApartmaniTable.setEditable(true);
        if (allApartments!=null) allApartments.clear();
        if (tableApartments!=null) tableApartments.clear();
        fillApartments();
    }

    private void fillApartments() {
        
        Korisnik myUser = MySession.getSessionInstance().getSessionUser();
        
        allApartments = MojiApartmaniModel.getAllMyApartments(myUser.getUserID());
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

        if (MojiApartmaniTable.getColumns().isEmpty()) {
            MojiApartmaniTable.getColumns().addAll(nameColumn, stateColumn, cityColumn, streetColumn, numberColumn);
        }
       
        MojiApartmaniTable.setItems(tableApartments);
        
    }

    @FXML
    private void IzmeniOdabraniApartman(ActionEvent event) {
        Apartman apartment=(Apartman) MojiApartmaniTable.getSelectionModel().getSelectedItem();
        if (apartment==null) return;
        new IzmenaApartmanaView().display(apartment, this);
    }
    
}
