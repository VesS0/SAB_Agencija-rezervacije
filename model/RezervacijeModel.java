/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DB.DB;
import entity.Apartman;
import entity.Rezervacija;
import entity.Soba;
import entity.Korisnik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlada
 */
public class RezervacijeModel {

    private static final String SELECT_CUSTOMER_RESERVATIONS_QUERY
            = "select RES.idRez, S.idApar, S.rb, RES.datumOd, RES.datumDo "
            + "from Rezervacija RES , Soba S "
            + "where S.idSob=RES.idSob AND RES.idKor = ?";

    private static final String SELECT_SELLER_RESERVATIONS_QUERY
            //U.ime, U.prezime, U.korime, U.Email, U.brTel, AP.naziv ,
            = "select RES.idRez, U.idKor, S.idApar, S.rb, RES.datumOd, RES.datumDo, U.korime, U.email, U.brTel, U.ime, U.prezime  "
            + "from Rezervacija RES, Apartman AP, Korisnik U , Soba S "
            + "where S.idSob=RES.idSob AND S.idApar = AP.idApar and RES.idKor = U.idKor and AP.idKor = ? ";
//Qime, QPrezime, QKorisnicko, QEmail, QTelefon, QBroj, QNazivAp, QAdresa;
    public static List<Rezervacija> getAllMyCustomerReservations(Korisnik user) {
        List<Rezervacija> rooms = new ArrayList();
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT_CUSTOMER_RESERVATIONS_QUERY);
            statement.setInt(1, user.getUserID());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Rezervacija reservation = new Rezervacija();

                reservation.setReservationId(result.getInt("idRez"));

                Apartman a = new Apartman();
                a.setApartmentId(result.getInt("idApar"));

                Soba r = new Soba();
                r.setRoomNo(result.getInt("rb"));

                reservation.setApartment(a);
                reservation.setRoom(r);

              

                reservation.setStartDate(result.getDate("datumOd"));
                reservation.setEndDate(result.getDate("datumDo"));
                rooms.add(reservation);
            }

            return rooms;

        } catch (SQLException ex) {
            Logger.getLogger(RezervacijeModel.class.getName()).log(Level.SEVERE, null, ex);
            return rooms;
        }
    }

    public static List<Rezervacija> getAllMySellerReservations(Korisnik myKorisnik) {
        List<Rezervacija> rooms = new ArrayList();
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT_SELLER_RESERVATIONS_QUERY);
            statement.setInt(1, myKorisnik.getUserID());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Rezervacija reservation = new Rezervacija();

                reservation.setReservationId(result.getInt("idRez"));

                Apartman a = new Apartman();
                a.setApartmentId(result.getInt("idApar"));

                Soba r = new Soba();
                r.setRoomNo(result.getInt("rb"));

                reservation.setApartment(a);
                reservation.setRoom(r);

                Korisnik u = new Korisnik();
                u.setUserID(result.getInt("idKor"));
                u.setUserName(result.getString("korime"));
                u.setName(result.getString("ime"));
                u.setSurrname(result.getString("prezime"));
                u.setEmail(result.getString("email"));
                u.setPhone(result.getString("brTel"));

                reservation.setUser(u);

                reservation.setStartDate(result.getDate("datumOd"));
                reservation.setEndDate(result.getDate("datumDo"));
                rooms.add(reservation);
            }

            return rooms;

        } catch (SQLException ex) {
            Logger.getLogger(RezervacijeModel.class.getName()).log(Level.SEVERE, null, ex);
            return rooms;
        }
    }

}
