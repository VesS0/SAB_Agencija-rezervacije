/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author vlada
 */
public class Soba {

    private Apartman apartment;
    private int roomNo;
    private int capacity;
    private String details;
    private boolean locked;
    private int myID;

    public Soba() {
    }

    public Soba(Apartman apartment, int roomNo, int capacity, String details, boolean locked) {
        this.apartment = apartment;
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.details = details;
        this.locked = locked;
    }
    public void setmyID(int id)
    {
        myID=id;
    }

    public Apartman getApartment() {
        return apartment;
    }

    public void setApartment(Apartman apartment) {
        this.apartment = apartment;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details.trim();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getRoomID() {
        return myID;
    }
}
