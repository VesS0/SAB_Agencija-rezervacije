/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  DB.DB
 *  entiteti.Korisnik
 *  entiteti.Kupac
 *  entiteti.Prodavac
 *  model.LoginModel
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

public class TemplateModel {
    private static final String SELECT_CUSTOMER_QUERY = "select KO.idKor, KO.email, KO.brTel, KO.ime, KO.prezime, KU.brKart from Kupac KU, Korisnik KO where KO.idKor = KU.idKor and KO.idKor=?";
    private static final String SELECT_SELLER_QUERY = "select KO.idKor, KO.email, KO.brTel, KO.ime, KO.prezime, KU.POS from Prodavac KU, Korisnik KO where KO.idKor = KU.idKor and KO.idKor=?";

    public static Kupac getUserCustomer(Korisnik myUser) {
        Kupac customer = null;
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT_CUSTOMER_QUERY);
            statement.setInt(1, myUser.getUserID());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String email = result.getString("email");
                String phone = result.getString("brTel");
                String name = result.getString("ime");
                String surrname = result.getString("prezime");
                String cardNo = result.getString("brKart");
                customer = new Kupac(myUser.getUserID(), myUser.getUserName(), myUser.getUserPass(), email, phone, name, surrname, cardNo);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static Prodavac getUserSeller(Korisnik myUser) {
        Prodavac seller = null;
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT_SELLER_QUERY);
            statement.setInt(1, myUser.getUserID());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String email = result.getString("email");
                String phone = result.getString("brTel");
                String name = result.getString("ime");
                String surrname = result.getString("prezime");
                String pos = result.getString("POS");
                seller = new Prodavac(myUser.getUserID(), myUser.getUserName(), myUser.getUserPass(), email, phone, name, surrname, pos);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seller;
    }
}
