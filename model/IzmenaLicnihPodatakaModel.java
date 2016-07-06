/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  DB.DB
 *  model.LoginModel
 */
package model;

import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IzmenaLicnihPodatakaModel {
    private static final String CHANGE_USER_PASSWORD_QUERY = "update Korisnik set sifra = ? where idKor = ?";
    private static final String CHANGE_USER_NAME_QUERY = "update Korisnik set ime = ? where  idKor = ?";
    private static final String CHANGE_USER_SURRNAME_QUERY = "update Korisnik set prezime = ? where  idKor = ?";
    private static final String CHANGE_USER_EMAIL_QUERY = "update Korisnik set email = ? where  idKor = ?";
    private static final String CHANGE_USER_PHONE_QUERY = "update Korisnik set brTel = ? where  idKor = ?";
    private static final String CHANGE_SELLER_POS_QUERY = "update Prodavac set POS = ? where  idKor = ?";
    private static final String CHANGE_CUSTOMER_CARD_QUERY = "update Kupac set brKart = ? where  idKor = ?";
    private static final String CHANGE_USER_USERNAME_QUERY = "update Korisnik set korime=? where  idKor = ?";

    public static boolean changeUserPassword(String oldPass, String newPass, String userId) {
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(CHANGE_USER_PASSWORD_QUERY);
            statement.setString(1, newPass);
            statement.setString(2, oldPass);
            statement.setString(3, userId);
            int rowCount = statement.executeUpdate();
            if (rowCount == 0) {
                return false;
            }
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void changeUserData(String userId, String name, String surrname, String email, String phone, String sellerCustomer, String username, String password, boolean isSeller) {
        Connection conn = DB.getDBInstance().getConnection();
        try {
            PreparedStatement statement;
            if (name != null && !name.trim().equals("")) {
                statement = conn.prepareStatement(CHANGE_USER_NAME_QUERY);
                statement.setString(1, name);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (surrname != null && !surrname.trim().equals("")) {
                statement = conn.prepareStatement(CHANGE_USER_SURRNAME_QUERY);
                statement.setString(1, surrname);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (username != null && !username.trim().equals("")) {
                statement = conn.prepareStatement(CHANGE_USER_USERNAME_QUERY);
                statement.setString(1, username);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (email != null && !email.trim().equals("")) {
                statement = conn.prepareStatement(CHANGE_USER_EMAIL_QUERY);
                statement.setString(1, email);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (phone != null && !phone.trim().equals("")) {
                statement = conn.prepareStatement(CHANGE_USER_PHONE_QUERY);
                statement.setString(1, phone);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (sellerCustomer != null && !sellerCustomer.trim().equals("")) {
                statement = null;
                statement = isSeller ? conn.prepareStatement(CHANGE_SELLER_POS_QUERY) : conn.prepareStatement(CHANGE_CUSTOMER_CARD_QUERY);
                statement.setString(1, sellerCustomer);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
            if (password!=null && !password.trim().equals(""))
            {
                statement = conn.prepareStatement(CHANGE_USER_PASSWORD_QUERY);
                statement.setString(1, password);
                statement.setString(2, userId);
                statement.executeUpdate();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
