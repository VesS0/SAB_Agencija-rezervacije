/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DB.DB;
import entity.Apartman;
import entity.Soba;
import com.sun.deploy.model.LocalApplicationProperties;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.MySession;

/**
 *
 * @author vlada
 */
public class SobaModel {

    private static final String INSERT_ROOM_QUERY
            = "insert into Soba(idApar, rb, brOsoba, opis, zakljucano) "
            + "values(?,?,?,?,?)";

    private static final String SELECT_ROOMS_QUERY
            = "select idSob, rb, brOsoba, opis, zakljucano "
            + "from Soba "
            + "where idApar = ?";

    private static final String UPDATE_ROOM_QUERY
            = "update Soba "
            + "set brOsoba = ? , opis = ?, zakljucano = ? "
            + "where idApar = ? and rb = ?";

    private static final String DELETE_ROOM_QUERY
            = "delete from Soba where idApar = ? and rb = ?";

    public static final String SELECT_RESERVABLE_ROOMS_QUERY
            = "select S.idSob, S.rb, S.brOsoba, S.opis, S.zakljucano "
            + "from Soba S "
            + "where S.idApar = ? and S.zakljucano = 0 AND S.idSob not in "
            + "("
            + "   select R.idSob "
            + "   from Rezervacija R "
            + "   where R.idSob=S.idSob AND (? < R.DatumDo AND ? > R.DatumOd)"
            + ")";
    private static final String RESERVE_ROOM_QUERY
            = "insert into Rezervacija(idKor, idSob, DatumOd, DatumDo) "
            + " values(?,?,?,?)";

    private static final String CHECK_ROOM_RESERVATIONS_QUERY
            = " select idRez"
            + " from Rezervacija "
            + " where idSob=?";
    
    private static final String FIND_ROOM_ID
            = " select idSob"
            + " from Soba "
            + " where idApar=? and rb=?";
    
    private static final String CHECK_LOCKED_QUERY
            = "select S.idSob"
            + " from Soba S , Apartman A "
            + " where S.idSob=? AND (S.zakljucano=? OR (S.idApar=A.idApar AND A.zakljucan=?)) ";
    
    public static boolean addNewRoom(Soba newRoom) {
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT_ROOM_QUERY);
            int idSob;
            
            PreparedStatement statementt = conn.prepareStatement(FIND_ROOM_ID);

            statementt.setInt(1, newRoom.getApartment().getApartmentId());
            statementt.setInt(2, newRoom.getRoomNo());
            //statement.setDate(3, date);
       
            ResultSet rs = statementt.executeQuery();
            if (rs.next()) {
                idSob=rs.getInt("idSob");
                return false;
            }
            statement.setInt(1, newRoom.getApartment().getApartmentId());
            statement.setInt(2, newRoom.getRoomNo());
            statement.setInt(3, newRoom.getCapacity());
            statement.setString(4, newRoom.getDetails());
            statement.setBoolean(5, newRoom.isLocked());

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<Soba> getAllRooms(Apartman apartment) {
        List rooms = new ArrayList();
        try {

            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(SELECT_ROOMS_QUERY);
            statement.setInt(1, apartment.getApartmentId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Soba room = new Soba();
                room.setmyID(result.getInt("idSob"));///
                room.setApartment(apartment);
                room.setRoomNo(result.getInt("rb"));
                room.setCapacity(result.getInt("brOsoba"));
                room.setDetails(result.getString("opis"));
                room.setLocked(result.getBoolean("zakljucano"));
                rooms.add(room);
            }

            return rooms;

        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return rooms;
        }
    }

    public static boolean updateRoom(Soba myRoom) {
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_ROOM_QUERY);

            statement.setInt(1, myRoom.getCapacity());
            statement.setString(2, myRoom.getDetails());
            statement.setBoolean(3, myRoom.isLocked());
            statement.setInt(4, myRoom.getApartment().getApartmentId());
            statement.setInt(5, myRoom.getRoomNo());

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean deleteRoom(Soba myRoom) {
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(DELETE_ROOM_QUERY);

            statement.setInt(1, myRoom.getApartment().getApartmentId());
            statement.setInt(2, myRoom.getRoomNo());

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<Soba> getReservableRooms(Apartman apartment, Date startDate, Date endDate) {

        List<Soba> list = new ArrayList<>();

        try {
            Connection connection = DB.getDBInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_RESERVABLE_ROOMS_QUERY);

            statement.setInt(1, apartment.getApartmentId());
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Soba newRoom = new Soba();
                newRoom.setmyID(result.getInt("idSob"));///
                newRoom.setApartment(apartment);
                newRoom.setRoomNo(result.getInt("rb"));
                newRoom.setCapacity(result.getInt("brOsoba"));
                newRoom.setDetails(result.getString("opis"));
                newRoom.setLocked(result.getBoolean("zakljucano"));
                list.add(newRoom);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static boolean reserveRoom(Soba myRoom, Date startDate, Date endDate) {
        try {
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement checkLocked =conn.prepareStatement(CHECK_LOCKED_QUERY);
            
            checkLocked.setInt(1, myRoom.getRoomID());
            checkLocked.setBoolean(2, true);
            checkLocked.setBoolean(3, true);
            ResultSet rs=checkLocked.executeQuery();
            if (rs.next()) {
                System.out.println("Pokusaj rezervacije zakljucane sobe");
                return false;
            }
            
            PreparedStatement statement = conn.prepareStatement(RESERVE_ROOM_QUERY);

            statement.setInt(1, MySession.getSessionInstance().getSessionUser().getUserID());
            statement.setInt(2, myRoom.getRoomID());
            statement.setDate(3, startDate);
            statement.setDate(4, endDate);

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean checkReservationsFromRoom(Soba myRoom) {//, Date date
        try {
            int idSob;
            Connection conn = DB.getDBInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement(FIND_ROOM_ID);

            statement.setInt(1, myRoom.getApartment().getApartmentId());
            statement.setInt(2, myRoom.getRoomNo());
            //statement.setDate(3, date);
       
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                idSob=rs.getInt("idSob");
                System.out.println(idSob);
                PreparedStatement statementt = conn.prepareStatement(CHECK_ROOM_RESERVATIONS_QUERY);
                statementt.setInt(1, idSob);
                ResultSet rss = statementt.executeQuery();
                if(rss.next()){
                    return true;
                }
            }
                     
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SobaModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
