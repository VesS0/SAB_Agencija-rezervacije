/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Apartman;
import entity.Soba;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.SobaModel;
import static model.SobaModel.getReservableRooms;
import session.MyMessage;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class RezervacijaSobeController implements Initializable {

    @FXML
    private Pane innerRoomSelector;
   
    @FXML
    private TableView tabela;
    @FXML
    private Button rezervisi;
    @FXML
    private Label errorLabel;

    @FXML
    private TextField DatumDo;
    @FXML
    private TextField DatumOd;
    @FXML
    private TextField RoomCap;
    @FXML
    private TextField RoomNum;
    @FXML
    private TextField RoomDetails;
    @FXML
    private Button search;
    
    private Apartman myApartment;
    private Date datumOd,datumDo;
        
    private List<Soba> allRooms;
    private ObservableList<Soba> tableRooms;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    public void display(Apartman myApartment) {
        this.myApartment=myApartment;
        
        DatumOd.setPromptText("dd-MM-yyyy");
        DatumDo.setPromptText("dd-MM-yyyy");
        
        rezervisi.setDisable(true);
        
        allRooms = SobaModel.getAllRooms(myApartment);
        tableRooms = FXCollections.observableArrayList(allRooms);
        setColumns();
        tabela.setItems(tableRooms);
        
                tabela.setOnMouseClicked((MouseEvent e) -> {
            if (e.getClickCount()>1) {
                
                Soba soba = (Soba) tabela.getSelectionModel().getSelectedItem();
                clearRoomText();
                if (soba==null) return;
                RoomNum.setText(""+soba.getRoomNo());
                RoomCap.setText(""+soba.getCapacity());
                RoomDetails.setText(""+soba.getDetails()); 
                
            }
        });
    }
    
    private void setColumns()
    {
        
        TableColumn<Soba, String> roomNoColumn = new TableColumn<>("Br Sobe");
        roomNoColumn.setMinWidth(70);
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<Soba, String>("roomNo"));

        TableColumn capacityColumn = new TableColumn("Kapacitet");
        capacityColumn.setMinWidth(75);
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Soba, String>("capacity"));

        TableColumn detailsColumn = new TableColumn("Opis");
        detailsColumn.setMinWidth(130);
        detailsColumn.setCellValueFactory(new PropertyValueFactory<Soba, String>("details"));

        TableColumn lockedColumn = new TableColumn("Zakljucana");
        lockedColumn.setMinWidth(62);
        lockedColumn.setCellValueFactory(new PropertyValueFactory<Soba, String>("locked"));

        tabela.getColumns().addAll(roomNoColumn, capacityColumn, detailsColumn, lockedColumn);


        if (tabela.getColumns().isEmpty()) {
            tabela.getColumns().addAll(roomNoColumn, capacityColumn, detailsColumn, lockedColumn);
        }
    }
    
    private void clearRoomText() {  
                RoomNum.setText("");
                RoomCap.setText("");
                RoomDetails.setText("");       
    }

    @FXML
    private void PretraziSlobodne(ActionEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dod,ddo;
        try {
            if (    ((((DatumOd == null || DatumDo == null) || DatumOd.getText() == null) || DatumDo.getText() == null) || DatumOd.getText().trim().equals(""))||
                    DatumOd.getText().trim().equals("")) {
                throw new Exception();
            } else { 
            }
                
            ddo = (java.util.Date) sdf.parse(DatumOd.getText());
            dod = (java.util.Date) sdf.parse(DatumDo.getText());
            
            datumOd = new java.sql.Date(dod.getTime());
            datumDo=new java.sql.Date(ddo.getTime());
        } catch (java.lang.Exception ex) {
            Logger.getLogger(RezervacijaSobeController.class.getName()).log(Level.SEVERE, null, ex);
            MyMessage.setErrorLabel(errorLabel, "Los format! Unesite dd-MM-yyyy!", true);
            return;
        }
        
        allRooms=SobaModel.getReservableRooms(myApartment, datumOd, datumDo);
        tableRooms = FXCollections.observableArrayList(allRooms);
        setColumns();
        tabela.setItems(tableRooms);
        
        rezervisi.setDisable(false);
    }
    
    
    @FXML
    private void reserveSelected(ActionEvent event) { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dod,ddo;
        try {
            if (    DatumOd==null || 
                    DatumDo==null || 
                    DatumOd.getText()==null || DatumDo.getText()==null ||
                    DatumOd.getText().trim().equals("")||
                    DatumOd.getText().trim().equals("")) 
                throw new Exception();
                
            ddo = (java.util.Date) sdf.parse(DatumOd.getText());
            dod = (java.util.Date) sdf.parse(DatumDo.getText());
            
            datumOd = new java.sql.Date(dod.getTime());
            datumDo=new java.sql.Date(ddo.getTime());
        } catch (java.lang.Exception ex) {
            Logger.getLogger(RezervacijaSobeController.class.getName()).log(Level.SEVERE, null, ex);
            MyMessage.setErrorLabel(errorLabel, "Los format! Unesite dd-MM-yyyy!", true);
            return;
        }        
          Soba myRoom = (Soba) tabela.getSelectionModel().getSelectedItem();
          
          if (myRoom==null) {MyMessage.setErrorLabel(errorLabel, "Niste izabrali sobu! ", true); return;}
          
        if (SobaModel.reserveRoom(myRoom, datumOd,datumDo )) {
            MyMessage.setErrorLabel(errorLabel, "Rezervacija prihvacena", false);
            clearRoomText();
        } else {
            MyMessage.setErrorLabel(errorLabel, "Rezervacija odbijena!", true);
        }
    }
}
