/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session.MyMessage;
import entity.Apartman;
import entity.Korisnik;
import entity.Soba;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.MojiApartmaniModel;
import model.SobaModel;
import session.MySession;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class DodavanjeSobaController implements Initializable {

    @FXML
    private Pane innerRoomSelector;
    @FXML
    private TextField roomNoTextField;
    @FXML
    private TextField roomCapacityTextField;
    @FXML
    private TextField roomDetailTextField;
    @FXML
    private Button add;
    @FXML
    private Label errorLabel;
    private Apartman myApartment;
    @FXML
    private CheckBox zakljucana;
    @FXML
    private TableView tabela;
    
    
    private List<Soba> allRooms;
    private ObservableList<Soba> tableRooms;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DodajSobu(ActionEvent event) {
        String roomNo = roomNoTextField.getText();
        String roomCapacity = roomCapacityTextField.getText();
        String roomDetail = roomDetailTextField.getText();

        if (roomNo == null || roomNo.trim().equals("")
                || roomCapacity == null || roomCapacity.trim().equals("")
                || roomDetail == null || roomDetail.trim().equals("")) {
            MyMessage.setErrorLabel(errorLabel, "Neka polja su prazna!", true);
            return;
        }

        int roomNumber = -1;
        try {
            roomNumber = Integer.parseInt(roomNo);
        } catch (NumberFormatException nfe) {
            MyMessage.setErrorLabel(errorLabel, "Broj sobe nije dobro unesen!", true);
            return;
        }

        int capacity = -1;
        try {
            capacity = Integer.parseInt(roomCapacity);
        } catch (NumberFormatException nfe) {
            MyMessage.setErrorLabel(errorLabel, "Kapacitet lose unesen", true);
            return;
        }

        boolean isChecked=zakljucana.isSelected();
        
        if ("Dodaj Sobu".equals(add.getText())){
        if (SobaModel.addNewRoom(new Soba(myApartment, roomNumber, capacity, roomDetail, isChecked))) {
            MyMessage.setErrorLabel(errorLabel, "Uspesno ste dodali sobu", false);
            clearRoomText();
        } else {
            MyMessage.setErrorLabel(errorLabel, "Neuspesno dodavanje", true);
        }
        }
        else
        {
            Soba soba=(Soba) tabela.getSelectionModel().getSelectedItem();
            soba.setApartment(myApartment); 
            soba.setRoomNo(roomNumber); 
            soba.setCapacity(capacity);
            soba.setDetails(roomDetail); 
            soba.setLocked(isChecked);
            if (SobaModel.updateRoom(soba)){
                MyMessage.setErrorLabel(errorLabel, "Uspesno ste izmenili sobu", false);
                clearRoomText();
            }
            else
            {
                MyMessage.setErrorLabel(errorLabel, "Neuspesna izmena", true);
            }
        }
        
        //fillRooms();
    }
    
     private void clearRoomText() {
        roomNoTextField.setText("");
        roomCapacityTextField.setText("");
        roomDetailTextField.setText("");

    }

    public void display(Apartman myApartment) {
        this.myApartment=myApartment;
        clearRoomText();
        
          
        Korisnik myUser = MySession.getSessionInstance().getSessionUser();
        


        allRooms = SobaModel.getAllRooms(myApartment);
        tableRooms = FXCollections.observableArrayList(allRooms);

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
        tabela.setItems(tableRooms);
        
//        allRooms = SobaModel.getAllRooms(myApartment);
//        tableRooms = FXCollections.observableArrayList(allRooms);
//        tabela.setItems(tableRooms);
        tabela.setOnMouseClicked((MouseEvent e) -> {
            if (e.getClickCount()>1) {
                add.setText("Sacuvaj");
                
                Soba soba = (Soba) tabela.getSelectionModel().getSelectedItem();
                clearRoomText();
                
                roomNoTextField.setText(""+soba.getRoomNo());
                roomCapacityTextField.setText(""+soba.getCapacity());
                roomDetailTextField.setText(""+soba.getDetails());
                zakljucana.setSelected(soba.isLocked());

            }
        });
    }
}
