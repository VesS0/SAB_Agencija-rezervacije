/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author vlada
 */
public class Rezervacija {
//RES.idRez, U.idKor, S.idApar, S.rb, RES.datumOd, RES.datumDo, U.korime, U.email, U.brTel, U.ime, U.prezime "

    private String Qime, QPrezime, QKorisnicko, QEmail, QTelefon, QBroj, QNazivAp, QAdresa;

    private int reservationId;
    private Korisnik user;
    private Soba room;
    private Apartman apartment;
    private Date startDate;
    private Date endDate;

    private int roomNo;
    // private 

    public String getQime() {
        return Qime;
    }

    public void setQime(String Qime) {
        this.Qime = Qime;
    }

    public String getQPrezime() {
        return QPrezime;
    }

    public void setQPrezime(String QPrezime) {
        this.QPrezime = QPrezime;
    }

    public String getQKorisnicko() {
        return QKorisnicko;
    }

    public void setQKorisnicko(String QKorisnicko) {
        this.QKorisnicko = QKorisnicko;
    }

    public String getQEmail() {
        return QEmail;
    }

    public void setQEmail(String QEmail) {
        this.QEmail = QEmail;
    }

    public String getQTelefon() {
        return QTelefon;
    }

    public void setQTelefon(String QTelefon) {
        this.QTelefon = QTelefon;
    }

    public String getQBroj() {
        return QBroj;
    }

    public void setQBroj(String QBroj) {
        this.QBroj = QBroj;
    }

    public String getQNazivAp() {
        return QNazivAp;
    }

    public void setQNazivAp(String QNazivAp) {
        this.QNazivAp = QNazivAp;
    }

    public String getQAdresa() {
        return QAdresa;
    }

    public void setQAdresa(String QAdresa) {
        this.QAdresa = QAdresa;
    }

    public Rezervacija() {
    }

    public Rezervacija(int reservationID, Korisnik user, Soba room, Apartman a, Date checkIn, Date checkOut) {
        this.reservationId = reservationID;
        this.user = user;
        this.room = room;
        this.apartment = a;
        this.startDate = checkIn;
        this.endDate = checkOut;
    }

    public Korisnik getUser() {
        return user;
    }

    public void setUser(Korisnik user) {
        this.user = user;
    }

    public Soba getRoom() {
        return room;
    }

    public void setRoom(Soba room) {
        this.room = room;
        roomNo = room.getRoomNo();
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Apartman getApartment() {
        return apartment;
    }

    public void setApartment(Apartman apartment) {
        this.apartment = apartment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

}
