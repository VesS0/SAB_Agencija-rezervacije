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
public class Apartman {

    private int apartmentId;
    private String name;
    private String description;
    private Adresa address;
    private int sellerId;
    private boolean isLocked;

    private String state;
    private String city;
    private String street;
    private String number;

    public Apartman() {
    }

    public Apartman(int apartmentId, String name, String description, Adresa address, int sellerId, boolean isLocked) {
        this.apartmentId = apartmentId;
        this.name = name.trim();
        this.description = description.trim();
        this.address = address;
        this.sellerId = sellerId;
        this.isLocked = isLocked;

        setAddressFields();
    }

    private void setAddressFields() {
        this.state = address.getState();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public Adresa getAddress() {
        return address;
    }

    public void setAddress(Adresa address) {
        this.address = address;
        setAddressFields();
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
