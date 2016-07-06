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
 * @author vlada
 */
public class LoginModel {
   // String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static final String LOGIN_USER_QUERY = "select idKor from Korisnik where KO.korime = ? and KO.sifra = ?";
    
    private static final String LOGIN_CUSTOMER_QUERY = "select KO.idKor, KO.email, KO.brTel, KO.ime, KO.prezime, KU.brKart"
            + " from Kupac KU, Korisnik KO"
            + " where KO.idKor = KU.idKor and KO.korime = ? and KO.sifra = ?";

    private static final String LOGIN_SELLER_QUERY = "select KO.idKor, KO.email, KO.brTel, KO.ime, KO.prezime, KU.POS"
            + " from Prodavac KU, Korisnik KO"
            + " where KO.idKor = KU.idKor and KO.korime = ? and KO.sifra = ?";
    
     private static Korisnik getKorisnikFrom(ResultSet result,String userName, String userPass, boolean kupac) throws SQLException
    {
           if (result.next()) {
                int userId = result.getInt("idKor");
                String email = result.getString("email");
                String phone = result.getString("brTel");
                String name = result.getString("ime");
                String surrname = result.getString("prezime");
                
                if (kupac) {
                    String cardNo = result.getString("brKart");
                    return new Kupac(userId, userName, userPass, email, phone, name, surrname, cardNo);
                }
                else
                {
                    String pos = result.getString("POS");
                    return new Prodavac(userId, userName, userPass, email, phone, name, surrname, pos);
                }
            }
           return null;
    }
     
    public static Kupac getUserCustomer(String userName, String userPass) {
        Kupac customer = null;
        try {
            ResultSet result=executeQuery(userName,userPass,LOGIN_CUSTOMER_QUERY);
            customer = (Kupac)getKorisnikFrom(result,userName,userPass,true);

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
   

    public static Prodavac getUserSeller(String userName, String userPass) {
        Prodavac seller = null;
        try {
            ResultSet result=executeQuery(userName,userPass,LOGIN_SELLER_QUERY);
            seller = (Prodavac)getKorisnikFrom(result,userName,userPass,false);

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return seller;
    }
     
    private static ResultSet executeQuery(String userName,String userPass,String QUERY) throws SQLException
    {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QUERY);

            statement.setString(1, userName);
            statement.setString(2, userPass);
            
            return statement.executeQuery();
    }
}
