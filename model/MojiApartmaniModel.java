/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DB.DB;
import entity.Adresa;
import entity.Apartman;
import entity.Korisnik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlada
 */
public class MojiApartmaniModel {

    private static final String INSERT_ADDRESS_QUERY
            = "insert into Adresa(drzava, grad, ulica, broj) values (?,?,?,?)";

    private static final String INSERT_APARTMENT_QUERY
            = "insert into Apartman(naziv, opis, zakljucan, idKor, idAdr) values (?,?,?,?,?)";

    private static final String SELECT_MY_APARTMENTS_QUERY
            = "select idApar, naziv, opis, zakljucan, idKor, drzava, grad, ulica, broj "
            + "from Apartman AP, Adresa AD "
            + "where AD.idAdr = AP.idAdr and idKor = ?";

    private static final String SELECT_APARTMENTS_QUERY
            = "select idApar, naziv, opis, zakljucan, idKor, drzava, grad, ulica, broj "
            + "from Apartman AP, Adresa AD "
            + "where AD.idAdr = AP.idAdr";

    private static final String SELECT_APARTMENT_QUERY
            = "select idApar, naziv, opis, zakljucan, idKor, drzava, grad, ulica, broj "
            + "from Apartman AP, Adresa AD "
            + "where AD.idAdr = AP.idAdr and AP.idApar = ?";

    private static final String UPDATE_APARTMENT_QUERY
            = "update Apartman set naziv = ? , opis = ?, zakljucan = ? where idApar = ?";

    private static final String FILTER_STATE_CITY_APARTMENTS_QUERY
            = "select idApar, naziv, opis, zakljucan, idKor, drzava, grad, ulica, broj "
            + "from Apartman AP, Adresa AD "
            + "where AD.idAdr = AP.idAdr and drzava LIKE ? and grad LIKE ?";

    private static final String SELECT_USER_BY_APARTMENT_QUERY
            = "select U.idKor, U.korime, U.email, U.brTel, U.ime, U.prezime "
            + "from Korisnik U, Apartman AP "
            + "where U.idKor = AP.idKor and AP.idApar = ?";
    private static List<Apartman> getList(ResultSet result) throws SQLException
    {
        List list=new ArrayList();
        
        while (result.next()) {
                entity.Adresa address = new entity.Adresa();
                address.setState(result.getString("Drzava"));
                address.setCity(result.getString("Grad"));
                address.setStreet(result.getString("Ulica"));
                address.setNumber(result.getString("Broj"));

                entity.Apartman apartment = new entity.Apartman();
                apartment.setAddress(address);
                apartment.setApartmentId(result.getInt("idApar"));
                apartment.setName(result.getString("naziv"));
                apartment.setDescription(result.getString("Opis"));
                apartment.setIsLocked(result.getBoolean("Zakljucan"));
                list.add(apartment);
            }

            return list;
    }
    public static List<Apartman> getAllApartments() {
        try {

            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_APARTMENTS_QUERY);
            ResultSet result = statement.executeQuery();
           return getList(result);
        } catch (SQLException ex) {
            Logger.getLogger(MojiApartmaniModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Apartman> getAllMyApartments(int userID) {
        try {
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_MY_APARTMENTS_QUERY);
            statement.setInt(1, userID);
            
            ResultSet result = statement.executeQuery();

            return getList(result);
        } catch (SQLException ex) {
            Logger.getLogger(MojiApartmaniModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Apartman> filterStateCityApartments(String stateValue, String cityValue) {
        try {
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FILTER_STATE_CITY_APARTMENTS_QUERY);
            statement.setString(1, "%" + stateValue + "%");
            statement.setString(2, "%" + cityValue + "%");
            ResultSet result = statement.executeQuery();
            
            return getList(result);
            
        } catch (SQLException ex) {
            Logger.getLogger(MojiApartmaniModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Apartman getApartment(int apartmentId) {
        Apartman apartment = null;
        try {
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_APARTMENT_QUERY);
            statement.setInt(1, apartmentId);
            ResultSet result = statement.executeQuery();

            return getList(result).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(MojiApartmaniModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static boolean insertNewApartment(int sellerId, String name, String description, String state, String city, String street, String number) {

        int adrId = insertNewAddress(state, city, street, number);
        if (adrId == -1) {
            return false;
        }
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT_APARTMENT_QUERY);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, 0);
            statement.setInt(4, sellerId);
            statement.setInt(5, adrId);


            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static int insertNewAddress(String state, String city, String street, String number) {
        int addrId = -1;
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT_ADDRESS_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, state);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setString(4, number);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                addrId = rs.getInt(1);
            }
            return addrId;

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addrId;
    }

    public static boolean updateApartment(Apartman apartment) {
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_APARTMENT_QUERY);

            statement.setString(1, apartment.getName());
            statement.setString(2, apartment.getDescription());
            statement.setBoolean(3, apartment.isIsLocked());
            statement.setInt(4, apartment.getApartmentId());

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    public static Korisnik getUserByApartment(int apartmentId) {
        Korisnik u = null;
        try {
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_APARTMENT_QUERY);
            statement.setInt(1, apartmentId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                u = new Korisnik();
                u.setUserID(result.getInt("idKor"));
                u.setUserName(result.getString("korime"));
                u.setName(result.getString("ime"));
                u.setSurrname(result.getString("prezime"));
                u.setEmail(result.getString("email"));
                u.setPhone(result.getString("brTel"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(MojiApartmaniModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
