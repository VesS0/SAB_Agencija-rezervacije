/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DB.DB;
import entity.Korisnik;
import entity.Kupac;
import entity.Prodavac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vladimir
 */
public class RegistracijaModel {
    private static final String REGISTER_USER_QUERY = "insert into Korisnik ( korime, sifra, ime, prezime, email, brTel) values (?, ?, ?, ?, ?, ?)";    
    
    private static final String GET_USERID_QUERY = "select idKor from Korisnik where korime = ?";    
    
     private static final String REGISTER_SELLER_QUERY = "insert into Prodavac (idKor, POS) values (?, ?)";     
     
    private static final String REGISTER_CUSTOMER_QUERY = "insert into Kupac (idKor, brKart) values (?, ?)";   

    private static int registerUser(Korisnik newUser) throws SQLException {
        Connection connection = DB.getDBInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(REGISTER_USER_QUERY);
        
        statement.setString(1, newUser.getUserName());
        statement.setString(2, newUser.getUserPass());
        statement.setString(3, newUser.getName());
        statement.setString(4, newUser.getSurrname());
        statement.setString(5, newUser.getEmail());
        statement.setString(6, newUser.getPhone());
        
        statement.executeUpdate();

        statement = connection.prepareStatement(GET_USERID_QUERY);
        
        statement.setString(1, newUser.getUserName());
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt("idKor");
        }

        return -1;
    }

    public static int registerSeller(Prodavac newSeller) {
        try {
            int userID = registerUser(newSeller);
            
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(REGISTER_SELLER_QUERY);

            statement.setInt(1, userID);
            statement.setString(2, newSeller.getPOS());
            statement.executeUpdate();

            return userID;
        } catch (SQLException ex) {
            Logger.getLogger(RegistracijaModel.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static int registerCustomer(Kupac newCustomer) {
        try {
            int userID = registerUser(newCustomer);
            
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(REGISTER_CUSTOMER_QUERY);
            
            statement.setInt(1, userID);
            statement.setString(2, newCustomer.getCardNo());
            statement.executeUpdate();

            return userID;
        } catch (SQLException ex) {
            Logger.getLogger(RegistracijaModel.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }


}
