/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Apartman;
import entity.Korisnik;
import entity.Kupac;
import entity.Prodavac;
import entity.Rezervacija;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MojiApartmaniModel;
import model.RezervacijeModel;
import session.MySession;
import view.IzmenaApartmanaView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class RezervacijeController implements Initializable {

    @FXML
    private TableView RezervacijeTable;
    
    private List<Rezervacija> allReservations;
    private ObservableList<Rezervacija> tableReservations;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showDetails(ActionEvent event) {
        
      
        Rezervacija rezervacija=(Rezervacija) RezervacijeTable.getSelectionModel().getSelectedItem();
        
        if (rezervacija==null) return;
        Apartman apartman=rezervacija.getApartment();
        new IzmenaApartmanaView().display(apartman, null);
    }

    public void display() {
        
        Korisnik myUser = MySession.getSessionInstance().getSessionUser();
        if (myUser instanceof Prodavac) {            
            allReservations = RezervacijeModel.getAllMySellerReservations(myUser);
            
        } else {
            allReservations = RezervacijeModel.getAllMyCustomerReservations(myUser);
        }
        
       tableReservations = FXCollections.observableArrayList(allReservations);
       //setColumnsRight(myUser);
       setColumnsWrong();
        RezervacijeTable.setItems(tableReservations);
    }
    
    public void setColumnsWrong()
    {
        TableColumn<Rezervacija, String> roomNoColumn = new TableColumn<>("Broj Sobe");
        roomNoColumn.setMinWidth(300);
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));

        TableColumn checkInColumn = new TableColumn("Datum Od");
        checkInColumn.setMinWidth(300);
        checkInColumn.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("startDate"));

        TableColumn checkOutColumn = new TableColumn("Datum Do");
        checkOutColumn.setMinWidth(300);
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("endDate"));

        RezervacijeTable.getColumns().addAll(roomNoColumn, checkInColumn, checkOutColumn);
    }
    public void setColumnsRight(Korisnik myUser)
    {
                
        TableColumn<Korisnik, String> nameColumn = new TableColumn<Korisnik, String>("Ime");
        nameColumn.setMinWidth(112);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("name"));
        
        TableColumn surnameColumn = new TableColumn("Prezime");
        surnameColumn.setMinWidth(112);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("surname"));
        
        TableColumn usernameColumn = new TableColumn("Korisnicko");
        usernameColumn.setMinWidth(112);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("username"));
        
        TableColumn emailColumn = new TableColumn("email");
        emailColumn.setMinWidth(112);
        emailColumn.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("email"));
        
        TableColumn phoneColumn = new TableColumn("Telefon");
        phoneColumn.setMinWidth(112);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("phone"));
        
        TableColumn numColumn = new TableColumn("Broj");
        numColumn.setMinWidth(111);
        if (myUser instanceof Prodavac) numColumn.setCellValueFactory(new PropertyValueFactory<Prodavac, String>("POS"));
        else numColumn.setCellValueFactory(new PropertyValueFactory<Kupac, String>("cardNo"));
        
        TableColumn nameApColumn = new TableColumn("Naziv ap");
        nameApColumn.setMinWidth(112);
        nameApColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("name"));
        
        TableColumn addressColumn = new TableColumn("Adresa");
        addressColumn.setMinWidth(117);
        addressColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("address"));
        
        
        RezervacijeTable.getColumns().addAll(nameColumn,surnameColumn,usernameColumn, emailColumn,phoneColumn,numColumn,nameApColumn,addressColumn);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 